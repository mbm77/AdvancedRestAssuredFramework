package java8.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TVSeriesData {

	public static List<String> getTVSeriesNames(int startYear, int endYear) {
		List<TVSeries> series = new ArrayList<>();
		series.add(new TVSeries("Game of Thrones", "(2011-2019)", "A", "57 min", "Action, Adventure, Drama", 9.3,
				"Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
				1773458, 1));
		series.add(new TVSeries("Breaking Bad", "(2008-2013)", "18", "49 min", "Crime, Drama, Thriller", 9.5,
				"A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
				1468887, 2));
		series.add(new TVSeries("The Walking Dead", "(2010- )", "18+", "44 min", "Drama, Horror, Thriller", 8.2,
				"Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins and must lead a group of survivors to stay alive.",
				854698, 3));

		series.add(new TVSeries("Friends", "(1994-2004)", "13+", "22 min", "Comedy, Romance", 8.9,
				"Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
				829816, 4));
		if (endYear == -1) {
			// System.out.println(endYear);System.exit(0);
			List<String> seriesNames = series.stream().filter(
					o -> Integer.parseInt(o.getRuntime_of_series().replaceAll("[()]", "").split("-")[0]) >= startYear)
					.filter(o -> o.getRuntime_of_series().replaceAll("[()]", "").split("-")[1].equals(" "))
					.map(o -> o.getName()).collect(Collectors.toList());
			return seriesNames;
		} else {
			List<String> seriesNames = series.stream().filter(
					o -> Integer.parseInt(o.getRuntime_of_series().replaceAll("[()]", "").split("-")[0]) >= startYear)
					.filter(o -> Integer
							.parseInt(o.getRuntime_of_series().replaceAll("[()]", "").split("-")[1]) <= endYear)
					.map(o -> o.getName()).collect(Collectors.toList());
			return seriesNames;
		}

	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("enter start year");
			String strStartDate = scanner.nextLine();
			int startYear = Integer.parseInt(strStartDate);
			System.out.println("enter end year");
			String strEndDate = scanner.nextLine();
			int endYear = Integer.parseInt(strEndDate);
			List<String> seriesNames = getTVSeriesNames(startYear, endYear);
			if (seriesNames == null || seriesNames.isEmpty()) {
				System.out.println("No series available");
			} else {
				System.out.println(seriesNames);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
