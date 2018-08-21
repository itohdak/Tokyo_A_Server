package com.example.apiSample.controller

import com.example.apiSample.model.Image
import com.example.apiSample.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.sql.rowset.serial.SerialBlob



@RestController
class ImageController(private val imageService: ImageService) {
    @PostMapping(
            value = ["/image/create"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addImage(@RequestParam("file") file: MultipartFile): Unit {
        val rawData: ByteArray = file.bytes
        var blob = SerialBlob(rawData)
        blob.setBytes(1, rawData)
        imageService.addImage("hoge", blob)
    }

    @GetMapping(
            value = ["/image"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllImages(): ArrayList<Image> {
        return imageService.getAllImages()
    }

    @GetMapping(
            value = ["/image/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getImageById(@PathVariable("id") id: Long): Image {
        // return imageService.getImageById(id)
        return imageService.getBlobById(id)
    }
}