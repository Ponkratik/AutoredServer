package com.ponkratov.autored.model;

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*;

@Entity
@Table(name = "attachment", schema = "autored")
class Attachment(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") private val id: Long = 0,
    @Basic @Column(name = "file_name") val fileName: String = "",
    @Basic @Column(name = "file_size") val fileSize: Int = 0,
    @Basic @Column(name = "supertype_entity_id") val supertypeEntityId: Long = 0,
    @Basic @Column(name = "attachment_type_id") val attachmentTypeId: Long = 0,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "supertype_entity_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) val supertypeEntityBySupertypeEntityId: SupertypeEntity? = null,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "attachment_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) val attachmentTypeByAttachmentTypeId: AttachmentType? = null
)