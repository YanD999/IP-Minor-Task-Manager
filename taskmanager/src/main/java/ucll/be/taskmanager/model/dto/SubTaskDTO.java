package ucll.be.taskmanager.model.dto;

import ucll.be.taskmanager.model.exception.DomainException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubTaskDTO {

    @NotNull
    private long subId;

    @NotEmpty
    private String subTitle;

    @NotEmpty
    private String subDescription;

    public SubTaskDTO() {}

    public long getId() {
        return subId;
    }

    public void setId(long subId) {
        if (subId < 1) throw new DomainException();
        this.subId = subId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        if (subTitle.trim().isEmpty()) throw new DomainException("Subtitle is null");
        this.subTitle = subTitle;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        if (subDescription.trim().isEmpty()) throw new DomainException("Subdescription is null");
        this.subDescription = subDescription;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", subTitle: " + getSubTitle() + ", subDescription: " + getSubDescription();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SubTaskDTO && this.getId() == ((SubTaskDTO) o).getId() && this.getSubTitle().equals(((SubTaskDTO) o).getSubTitle()) && this.getSubDescription().equals(((SubTaskDTO) o).getSubDescription());
    }
}
