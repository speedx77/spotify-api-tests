package pojo;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Items {

	
	//general and artist specific pojo
	private Map<String, String> external_urls;
    private Followers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private int popularity;
    private String type;
    private String uri;
    
    //album specific pojo
    private String album_type;
    private int total_tracks;
    private List<String> available_markets;
    private String release_date;
    private String release_date_precision;
    private List<Artist> artists;
    
    //playlist specific pojo
    private String collaborative;
    private String description;
    private Owner owner;
    private String primary_color;
    @JsonProperty("public")
    private boolean publicPlaylist;
    private String snapshot_id;
    private Tracks tracks;
    
    
    
	
	public Map<String, String> getExternal_urls() {
		return external_urls;
	}
	public void setExternal_urls(Map<String, String> external_urls) {
		this.external_urls = external_urls;
	}
	public Followers getFollowers() {
		return followers;
	}
	public void setFollowers(Followers followers) {
		this.followers = followers;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
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
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
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
	public String getAlbum_type() {
		return album_type;
	}
	public void setAlbum_type(String album_type) {
		this.album_type = album_type;
	}
	public int getTotal_tracks() {
		return total_tracks;
	}
	public void setTotal_tracks(int total_tracks) {
		this.total_tracks = total_tracks;
	}
	public List<String> getAvailable_markets() {
		return available_markets;
	}
	public void setAvailable_markets(List<String> available_markets) {
		this.available_markets = available_markets;
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
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public String getCollaborative() {
		return collaborative;
	}
	public void setCollaborative(String collaborative) {
		this.collaborative = collaborative;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public String getPrimary_color() {
		return primary_color;
	}
	public void setPrimary_color(String primary_color) {
		this.primary_color = primary_color;
	}
	public boolean isPublicPlaylist() {
		return publicPlaylist;
	}
	public void setPublicPlaylist(boolean publicPlaylist) {
		this.publicPlaylist = publicPlaylist;
	}
	public String getSnapshot_id() {
		return snapshot_id;
	}
	public void setSnapshot_id(String snapshot_id) {
		this.snapshot_id = snapshot_id;
	}
	public Tracks getTracks() {
		return tracks;
	}
	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}

	
    
	
	
	
}