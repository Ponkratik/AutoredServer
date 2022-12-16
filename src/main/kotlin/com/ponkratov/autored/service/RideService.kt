package com.ponkratov.autored.service

import com.ponkratov.autored.dto.response.RideResponse
import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.Ride
import com.ponkratov.autored.repository.RideRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class RideService {

    @Autowired
    private var _rideRepositpry: RideRepository? = null
    private val rideRepository get() = requireNotNull(_rideRepositpry)

    @Autowired
    private var _supertypeEntityService: SupertypeEntityService? = null
    private val supertypeEntityService get() = requireNotNull(_supertypeEntityService)

    @Autowired
    private var _advertisementService: AdvertisementService? = null
    private val advertisementService get() = requireNotNull(_advertisementService)

    @Autowired
    private var _userService: UserService? = null
    private val userService get() = requireNotNull(_userService)

    @Autowired
    private var _attachmentService: AttachmentService? = null
    private val attachmentService get() = requireNotNull(_attachmentService)

    fun startBooking(advId: Long, lessorId: Long, dateStart: Date): String {
        val ride = Ride(
            dateStart = dateStart,
            lessorId = lessorId,
            advertisementId = advId
        )
        val idObject = supertypeEntityService.getId()
        ride.id = idObject
        val bookResult = rideRepository.save(ride)

        return "Book executed successfully"
    }

    fun endRide(id: Long, dateEnd: Date): String {
        val result = rideRepository.setDateEndById(id, dateEnd)
        return if (result > 0) {
            "Ride ended successfully"
        } else {
            "Error: this ride can't be ended now!"
        }
    }

    fun signByLessor(
        id: Long,
        dateSignedLessor: Date
    ): String {
        val result = rideRepository.setDateSignedLessorById(id, dateSignedLessor)
        return if (result > 0) {
            "Act signed by lessor successfully"
        } else {
            "Error: can't sign act now"
        }
    }

    fun signByLessee(
        id: Long,
        dateSignedLessee: Date
    ): String {
        val result = rideRepository.setDateSignedLesseeById(id, dateSignedLessee)
        return if (result > 0) {
            "Act signed by lessee successfully"
        } else {
            "Error: can't sign act now"
        }
    }


    fun checkupCar(id: Long, files: List<MultipartFile>, type: AttachmentTypeEnum): String {
        files.forEach {
            attachmentService.uploadFile(it, id, AttachmentTypeEnum.TYPE_CAR_CHECK_PHOTO_BEFORE)
        }

        return "Files uploaded successfully"
    }

    fun getRidesByUserId(userId: Long): List<Ride> {
        return rideRepository.getAllByLessorId(userId)
    }

    fun getRidesByAdvertisementId(advertisementId: Long): List<Ride> {
        return rideRepository.getAllByAdvertisementId(advertisementId)
    }

    fun getRideDetails(rideId: Long): Ride {
        return rideRepository.getRideById(rideId)
    }

    fun getRideResponsesByAdvertisementId(advId: Long): List<RideResponse> {
        val list = getRidesByAdvertisementId(advId)
        return list.map {
            RideResponse(
                ride = it,
                user = userService.getUserById(it.lessorId),
                advertisementResponse = advertisementService.getAdvertisementResponse(it.advertisementId)
            )
        }
    }

    fun getRideResponsesByLessorId(lessorId: Long): List<RideResponse> {
        val list = getRidesByUserId(lessorId)
        return list.map {
            RideResponse(
                ride = it,
                user = userService.getUserById(requireNotNull(it.advertisementByAdvertisementId?.userId)),
                advertisementResponse = advertisementService.getAdvertisementResponse(it.advertisementId)
            )
        }
    }

    fun startRide(rideId: Long, dateStart: Date): String {
        val result = rideRepository.setDateStartById(rideId, dateStart)
        return if (result > 0) {
            "Ride started successfully"
        } else {
            "Error: this ride can't be started now!"
        }
    }
}