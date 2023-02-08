package com.sergify.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sergify.backend.payload.response.FileResponse;

@Service
public class FileManagerService {

    public Set<FileResponse> getMetadata(MultipartFile[] multipartFiles) throws IOException, UnsupportedTagException, InvalidDataException {
        Set<FileResponse> response = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
                File tempFile = File.createTempFile("temp", ".mp3");
                file.transferTo(tempFile);
                Mp3File mp3file = new Mp3File(tempFile);
                ID3v2 metadata = mp3file.getId3v2Tag();

                FileResponse fileResponse = FileResponse
                        .builder()
                        .artist(metadata.getArtist())
                        .album(metadata.getAlbum())
                        .title(metadata.getTitle())
                        .position(metadata.getTrack())
                        .cover(metadata.getAlbumImage())
                        .build();

                response.add(fileResponse);
            }

        return response;
    }
}
