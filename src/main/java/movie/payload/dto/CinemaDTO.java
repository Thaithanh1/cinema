package movie.payload.dto;

public class CinemaDTO {
    private String address;
    private String description;
    private String code;
    private String nameOfCinema;
    private boolean isActive;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameOfCinema() {
        return nameOfCinema;
    }

    public void setNameOfCinema(String nameOfCinema) {
        this.nameOfCinema = nameOfCinema;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
