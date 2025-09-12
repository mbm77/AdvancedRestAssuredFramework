package java8.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TVSeries {
	private String name;
	private String runtime_of_series;
	private String certificate;
	private String runtime_of_episodes;
	private String genre;
	private double imdb_rating;
	private String overview;
	private long no_of_votes;
	private int id;
	
}

