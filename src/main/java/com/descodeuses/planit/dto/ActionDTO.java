package com.descodeuses.planit.dto;

import java.time.LocalDate;
import java.util.List;

public class ActionDTO {
    private Long id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;
    private int priority;
    private Long projetId;
    private String description;
    private Long userId;
    private List<Long> memberIds;
    private List<String> memberNames;

    public ActionDTO() {}

    public ActionDTO(Long id, String title, boolean completed, LocalDate dueDate, int priority,
                     String description, Long projetId, Long userId, List<String> memberNames) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
        this.projetId = projetId;
        this.userId = userId;
        this.memberNames = memberNames;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean getCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public Long getProjetId() { return projetId; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getMemberIds() { return memberIds; }
    public void setMemberIds(List<Long> memberIds) { this.memberIds = memberIds; }

    public List<String> getMemberNames() { return memberNames; }
    public void setMemberNames(List<String> memberNames) { this.memberNames = memberNames; }
}
