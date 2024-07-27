public class GuestRequest {
    private String name;
    private long stayDuration;

    public GuestRequest(String name, long stayDuration) {
        this.name = name;
        this.stayDuration = stayDuration;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return stayDuration;
    }
}
