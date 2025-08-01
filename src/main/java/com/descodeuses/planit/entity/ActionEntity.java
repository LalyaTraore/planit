// ActionEntity.java
package com.descodeuses.planit.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean completed;

    private LocalDate dueDate;

    private Integer priority;

    private String description;

    @ManyToMany
    @JoinTable(
        name = "todo_contact",
        joinColumns = @JoinColumn(name = "todo_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<ContactEntity> members = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "projet_id", referencedColumnName = "id")
    private ProjetEntity projet;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UtilisateurEntity user;

    // --- Getters and Setters ---

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

    public Set<ContactEntity> getMembers() { return members; }
    public void setMembers(Set<ContactEntity> members) { this.members = members; }

    public ProjetEntity getProjet() { return projet; }
    public void setProjet(ProjetEntity projet) { this.projet = projet; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public UtilisateurEntity getUser() { return user; }
    public void setUser(UtilisateurEntity user) { this.user = user; }
   
}