b: build-npm build-maven
build: build-npm
	mvn clean install
build-npm:
	yarn install
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
install-update:
	npm install -g npm-check-updates
	cd kala-game-web && ncu -u && yarn
	cd mancalaje-fe && ncu -u && yarn
audit:
	cd kala-game-web && npm audit fix && yarn
	cd mancalaje-fe && npm audit fix && yarn

