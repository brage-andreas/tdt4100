package part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class for Comparators.
 *
 * getHeightComparator compares Players based on heights
 * getTrueShootingPercentageComparator compares Players based on shooting skills
 *
 * Some background concerning the latter:
 * In basketball, true shooting percentage is an advanced statistic that
 * measures a player's efficiency at shooting the ball. It is intended to more
 * accurately
 * calculate a player's shooting than field goal percentage, free throw
 * percentage, and
 * three-point field goal percentage taken individually. Two- and three-point
 * field goals and
 * free throws are all considered in its calculation. It is abbreviated TS%.
 *
 * TS% = pointsScored / 2 x (fieldGoalAttempts + (0.44 x freeThrowAttempts))
 * Example for a player's first game:
 * fieldGoalAttempts = 20, freeThrowAttempts = 5, pointsScored = 38 => TS% =
 * 0.8559 = 85.59%
 * Example for the same player's second game:
 * fieldGoalAttemps = 1, freeThrowAttemps = 0, pointsScored = 3 =>
 * trueShootingPercentage = 1.5 = 150%
 *
 * Season average true shooting percentage for this player (two matches):
 * (38 + 3) / 2 x ((20 + 1) + (0.44 x (5 + 0)) = 0.8836 = 88.36%
 */

public class BasketballComparators {

	/**
	 * @return a comparator that compares athletes based on their height. Using this
	 *         comparator, a player with height 220 will rank before a player with
	 *         height 205.
	 */
	public static Comparator<Player> getHeightComparator() {
		return (p1, p2) -> Integer.compare(p2.getHeight(), p1.getHeight());
	}

	/**
	 * @return A comparator that compares basketball players based on
	 *         their season average true shooting percentage.
	 *         The comparator will be used for sorting players based on
	 *         putting the player with the highest season average true shooting
	 *         percentage first.
	 *
	 *         If two players have equal season average true shooting percentage,
	 *         they should be compared alphabetically by name.
	 *
	 */
	public static Comparator<Player> getTrueShootingPercentageComparator() {
		return Comparator
				.comparingDouble(BasketballComparators::getSeasonAverageTS).reversed()
				.thenComparing(Player::getName);
	}

	private static double getSeasonAverageTS(final Player player) {
		final double pointsScored = player.getSeasonStats().stream().mapToInt(stats -> stats.getPointsScored()).sum();
		final double fieldGoalAttempts = player.getSeasonStats().stream()
				.mapToInt(stats -> stats.getFieldGoalAttempts()).sum();
		final double freeThrowAttempts = player.getSeasonStats().stream()
				.mapToInt(stats -> stats.getFreeThrowAttempts()).sum();
		return pointsScored / 2.0 * (fieldGoalAttempts + (0.44 * freeThrowAttempts)) * 100;
	}

	public static void main(String[] args) {
		// Disclaimer: The names and statistics below are just made up

		PlayerGameStat stat11 = new PlayerGameStat(38, 20, 5);
		PlayerGameStat stat12 = new PlayerGameStat(3, 1, 0);
		List<PlayerGameStat> ssj = new ArrayList<PlayerGameStat>();
		ssj.add(stat11);
		ssj.add(stat12);
		Player john = new Player("John", 202, ssj);

		PlayerGameStat stat21 = new PlayerGameStat(25, 10, 3);
		PlayerGameStat stat22 = new PlayerGameStat(22, 4, 1);
		List<PlayerGameStat> sse = new ArrayList<PlayerGameStat>();
		sse.add(stat21);
		sse.add(stat22);
		Player eric = new Player("Eric", 199, sse);

		PlayerGameStat stat31 = new PlayerGameStat(28, 10, 5);
		List<PlayerGameStat> ssp = new ArrayList<PlayerGameStat>();
		ssp.add(stat31);
		Player peter = new Player("Peter", 200, ssp);

		List<Player> players = new ArrayList<Player>();
		players.add(john);
		players.add(eric);
		players.add(peter);

		players.sort(getHeightComparator());
		System.out.println(players.stream().map(Player::getName).toList()); // [John, Peter, Eric]

		players.sort(getTrueShootingPercentageComparator());
		System.out.println(players.stream().map(Player::getName).toList()); // [Eric, Peter, John]
	}
}
