package com.workfront.internship.booklibrary.business;

import com.workfront.internship.booklibrary.common.Media;
import com.workfront.internship.booklibrary.dao.MediaDAO;
import com.workfront.internship.booklibrary.dao.MediaDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ${Sona} on 7/21/2016.
 */

@Component
public class MediaManagerImpl implements MediaManager{

    @Autowired
    private MediaDAO mediaDAO;

    @Override
    public int add(Media media) {
        if(mediaValidator(media)){
            mediaDAO.add(media);
            return media.getId();
        }
        return 0;
    }

    @Override
    public Media getMediaByID(int id) {
        if(id < 1){
            throw new IllegalArgumentException("Invalid id is entered");
        }
        Media media = mediaDAO.getMediaByID(id);
        if(mediaValidator(media)){
            return media;
        }
        return null;
    }

    @Override
    public List<Media> viewAllMedia() {
        List<Media> mediaList = mediaDAO.getAllMedia();
        if(mediaList != null){
            return mediaList;
        }
        return null;
    }

    @Override
    public List<Media> viewAllMediaByBookId(int bookId) {
        List<Media> mediaList = mediaDAO.getAllMediaByBook(bookId);
        if(mediaList != null){
            return mediaList;
        }
        return null;
    }

//    @Override
//    public List<Media> viewAllMediaByMediaType(int id) {
//        return null;
//    } // todo add DAO method

    @Override
    public Media update(Media media) {
        if(mediaValidator(media)){
            mediaDAO.updateMedia(media);
            return media;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if(id < 1){
            throw new IllegalArgumentException("Invalid id is entered");
        }
        if(id > 0){
            mediaDAO.deleteMedia(id);
            if(mediaDAO.getMediaByID(id) == null){
                return true;
            }
        }
        return false;
    }

    private boolean mediaValidator(Media media){
        return media != null && media.getLink() != null && media.getType() != null && media.getBook() != null;
    }
}
