package com.ponkratov.autored.model;

import javax.persistence.*;

@Entity
@Table(name = "attachment_type", schema = "autored")
class AttachmentType(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "type") var type: String? = null,
    @OneToMany(mappedBy = "attachmentTypeByAttachmentTypeId") var attachmentsById: MutableList<Attachment>? = null
)
