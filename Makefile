b: build-npm build-maven
build: build-npm
	mvn clean install
build-npm:
	cd kala-game-web && yarn
	cd mancalaje-fe && yarn
build-maven:
	mvn clean install -Pdemo -DskipTests
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker:
	docker-compose up -d --build --remove-orphans
docker-databases: stop local
build-images:
build-docker: stop no-test build-npm
	docker-compose up -d --build --remove-orphans
show:
	docker ps -a  --format '{{.ID}} - {{.Names}} - {{.Status}}'
docker-delete-idle:
	docker ps --format '{{.ID}}' -q --filter="name=mje_" | xargs docker rm
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=mje_" | xargs docker stop
	docker ps -a --format '{{.ID}}' -q --filter="name=mje_" | xargs docker rm
docker-cleanup: docker-delete
	docker images -q | xargs docker rmi
docker-delete-apps: stop
prune-all: stop
	docker ps -a --format '{{.ID}}' -q | xargs docker stop
	docker ps -a --format '{{.ID}}' -q | xargs docker rm
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
stop:
	docker-compose down --remove-orphans
minikube-vmware:
	minikube start --driver=vmware
install-snyk:
	npm i -g snyk
remove-lock-files:
	find . -name "package-lock.json" | xargs -I {} rm {}; \
	find . -name "yarn.lock" | xargs -I {} rm {};
update: remove-lock-files
	npm install -g npm-check-updates
	cd kala-game-web; \
 		npx browserslist --update-db; \
 		ncu -u; \
 		yarn
	cd mancalaje-fe; \
 		npx browserslist --update-db; \
 		ncu -u; \
 		yarn
update-browsers:
	cd kala-game-web && npx browserslist@latest --update-db
	cd mancalaje-fe && npx browserslist@latest --update-db
audit:
	cd kala-game-web && npm audit fix && yarn
	cd mancalaje-fe && npm audit fix && yarn
npm-test:
	cd kala-game-web && npm run coverage
	cd mancalaje-fe && npm run coverage
update-mac-os:
	brew upgrade npm
version-status:
	mvn versions:display-dependency-updates
version-update-maven:
	mvn versions:use-next-releases
	mvn versions:use-latest-releases
	mvn versions:use-releases
owasp:
	mvn clean install -Powasp
coverage-npm:
	cd mancalaje-fe && sudo npm i -g jest && npm run coverage
deps-cypress-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/cypressUpdateOne.sh | bash
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-node-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/nodeUpdatesOne.sh | bash
deps-quick-update: deps-cypress-update deps-plugins-update deps-java-update deps-node-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
dc-migration:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/setupDockerCompose.sh | bash
