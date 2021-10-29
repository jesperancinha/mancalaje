# Coffee cups Kalah Game

## Goals
The main goal of this implementation is to illustrate a couple of aspects of Software Architecture using the Spring Framework.

1. Locks on JPA data
2. Transactions
3. Spring Reactive
4. Spring Cache
5. Hazelcast (maybe)
6. Use case for AOP's

## How to play

This game is a variation of the original [Kalah Game](https://en.wikipedia.org/wiki/Kalah).
Rules:

1. Each player has six small bowls. They are called `washers`.
2. Each player has only one table. Table is called `display`.
3. Players can only pick their own cups in their own washers.
4. Each time players can only pick all cups at a time and there is no option to pick an arbitrary number.
5. The destination washer, is the last of the rollout and the user cannot choose it and cups are rolled out one by one sequential washers on a circular anti-clockwise direction.
6. The last location of the last cup in the rollout determines special rules
7. If the last cup falls into the opponents display table, player eliminates as many cups of the opponent as the number of rolled out cups. Cups to be removed are chosen randomly.
8. The opponent can counter-act by emptying all cups of the display. Empty cups are worth half a point. If all cups are empty or there are no cups, then there is no way to counter-act.
9. Cups get filled with coffee once they reach the display of the current player. One for each hit.
10. If the last cup in the rollout falls into a table the current player gets another turn regardless
11. Cups in the display cannot be moved.
12. If the last cup falls into an empty washer belonging to the current player, then the player can pick up the cups on the washer of the opponents side, take them and put them on the display without coffee.
13. If the last cup falls into an empty washer belonging to the opponent, then one coffee cup gets filled automatically in the display. If no cup is there, the player gets a free turn.
14. Game ends when no more cups are available in the washers
15. The winner is the player with more points. Each empty cup counts half a point and each filled cup counts one point. Points are rounded down or floored. This means that players can lose half a point in such cases.

## Management Rule

1. The user who created the board has to give permission to other players if they want to play in that board.
2. The user who created does not need permission to join a game.
3. The user who created the game can interrupt or delete the game at any time without warning to the players
4. Once a user joins a game, they need to way for just one more to join in.