package com.ponkratov.autored.model

enum class AttachmentTypeEnum(val id: Long) {
    TYPE_AVATAR(1),
    TYPE_PASSPORT(2),
    TYPE_DRIVER_LICENSE(3),
    TYPE_SUPPORT_REQUEST(4),
    TYPE_CAR_PHOTO(5),
    TYPE_CAR_CHECK_PHOTO_BEFORE(6),
    TYPE_CAR_CHECK_PHOTO_AFTER(7)
}