package ucll.be.taskmanager.model.entity;


import ucll.be.taskmanager.model.exception.DomainException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class SubTask {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String subTitle;

    @NotEmpty
    private String subDescription;

    public SubTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) throw new DomainException("Id is null");
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        if (subTitle == null || subTitle.trim().isEmpty()) throw new DomainException("SubTitle is leeg");
        this.subTitle = subTitle;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        if (subDescription == null || subDescription.trim().isEmpty()) throw new DomainException("Description is leeg");
        this.subDescription = subDescription;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ", subTitle: " + getSubTitle() + ", subDescription: " + getSubDescription();
    }

    @Override
    public boolean equals(Object o) {
        return ( o instanceof SubTask && this.getId() == (((SubTask) o).getId()) && this.getSubTitle().equals(this.getSubTitle()) && this.getSubDescription().equals(((SubTask) o).getSubDescription()));
    }
}
