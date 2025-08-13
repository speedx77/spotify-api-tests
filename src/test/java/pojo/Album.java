package pojo;

import java.util.List;
import java.util.Map;

public class Album {
	
	private String album_type;
	private List<Artist> artists;
	private List<String> available_markets;
	private Map<String, String> external_urls;
    private List<Image> images;
	private boolean is_playable;
	private String name;
	private String release_date;
	private String release_date_precision;
	private Integer total_tracks;
	private String type;
	private String uri;
	
	private String href;
	private String id;
	
	
	public String getAlbum_type() {
		return album_type;
	}
	public void setAlbum_type(String album_type) {
		this.album_type = album_type;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public List<String> getAvailable_markets() {
		return available_markets;
	}
	public void setAvailable_markets(List<String> available_markets) {
		this.available_markets = available_markets;
	}
	public Map<String, String> getExternal_urls() {
		return external_urls;
	}
	public void setExternal_urls(Map<String, String> external_urls) {
		this.external_urls = external_urls;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public boolean isIs_playable() {
		return is_playable;
	}
	public void setIs_playable(boolean is_playable) {
		this.is_playable = is_playable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getRelease_date_precision() {
		return release_date_precision;
	}
	public void setRelease_date_precision(String release_date_precision) {
		this.release_date_precision = release_date_precision;
	}
	public Integer getTotal_tracks() {
		return total_tracks;
	}
	public void setTotal_tracks(Integer total_tracks) {
		this.total_tracks = total_tracks;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
