package com.tracker.projects.ws.datasource.entities.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Tasks")
@DynamicUpdate
public class TaskEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "parent_project")
    private Integer projectId;
    @Column(name = "in_sprint")
    private Integer sprintId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "due_date")
    private Date dueDate;
    @Column(name = "story_points")
    private Integer storyPoints;
    @Column(name = "priority")
    private String priority;
    @Column(name = "type")
    private String type;
    @Column(name = "is_epic")
    private boolean isEpic = false;
    @Column(name = "linked_to_epic")
    private Integer epicId;
    @Column(name = "parent_story")
    private Integer storyId;
    @Column(name = "part_of")
    private Integer partOf;
    @Column(name = "partOf")
    private Integer blocks;
    @Column(name = "subtask_of")
    private Integer subtaskOf;
    @Column(name = "total_estimate")
    private Double estimate;
    @Column(name = "logged_work")
    private Double loggedWork;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "assigned_to")
    private Integer assignedTo;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "task_status")
    private String status;
    @Column(name = "github_id")
    private String githubId;
    @Column(name = "jira_id")
    private String jiratId;

    public TaskEntity() {
    }

    protected TaskEntity(AbstractTaskDto taskDto) {
        this.taskId = taskDto.id;
        this.title = taskDto.title;
        this.description = taskDto.description;
        this.projectId = taskDto.projectId;
        this.sprintId = taskDto.sprintId;
        this.epicId = taskDto.epicId;
        this.storyId = taskDto.storyId;
        this.startDate = taskDto.startDate;
        this.dueDate = taskDto.dueDate;
        this.priority = taskDto.priority.name();
        this.type = taskDto.type.name();
        this.estimate = taskDto.estimate;
        this.loggedWork = taskDto.loggedWork;
        this.status = taskDto.status.name();
        this.attachment = taskDto.attachment;
        this.assignedTo = taskDto.assignedTo;
        this.createdBy = taskDto.createdBy;
    }

    public TaskEntity(ResearchDto researchDto) {
        this((AbstractTaskDto) researchDto);
        this.partOf = researchDto.partOf;
    }

    public TaskEntity(DocumentationDto documentationDto) {
        this((ResearchDto) documentationDto);
    }

    public TaskEntity(DesignDto designDto) {
        this((ResearchDto) designDto);
    }

    public TaskEntity(IssueDto issueDto) {
        this((ResearchDto) issueDto);
        this.blocks = issueDto.blocks;
    }

    public TaskEntity(StoryDto storyDto) {
        this((IssueDto) storyDto);
        this.storyPoints = storyDto.storyPoints;
        this.isEpic = storyDto.isEpic;
    }

    public TaskEntity(TaskDto taskDto) {
        this((IssueDto) taskDto);
        this.subtaskOf = taskDto.subtaskOf;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getPartOf() {
        return partOf;
    }

    public void setPartOf(Integer partOf) {
        this.partOf = partOf;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getSubtaskOf() {
        return subtaskOf;
    }

    public void setSubtaskOf(Integer subtaskOf) {
        this.subtaskOf = subtaskOf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEpic() {
        return isEpic;
    }

    public void setEpic(boolean epic) {
        isEpic = epic;
    }

    public Double getEstimate() {
        return estimate;
    }

    public void setEstimate(Double estimate) {
        this.estimate = estimate;
    }

    public Double getLoggedWork() {
        return loggedWork;
    }

    public void setLoggedWork(Double loggedWork) {
        this.loggedWork = loggedWork;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getJiratId() {
        return jiratId;
    }

    public void setJiratId(String jiratId) {
        this.jiratId = jiratId;
    }
}
