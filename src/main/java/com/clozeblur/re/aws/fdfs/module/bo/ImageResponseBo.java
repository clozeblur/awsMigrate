//package com.clozeblur.re.aws.fdfs.module.bo;
//
//import org.apache.commons.imaging.ImageInfo;
//import org.apache.commons.imaging.ImageReadException;
//import org.apache.commons.imaging.Imaging;
//
//import java.io.IOException;
//import java.util.Date;
//
///**
// * 图片返回
// * Created by yuanjx on 2017/12/16.
// */
//public class ImageResponseBo {
//
//    private byte[] bytes;
//    private ImageInfo imageInfo;
//    private Date lastModified;
//
//    public ImageResponseBo(byte[] bytes, Date lastModified) {
//        this.bytes = bytes;
//        this.lastModified = lastModified;
//        try {
//            this.imageInfo = Imaging.getImageInfo(bytes);
//        } catch (ImageReadException | IOException e) {
//            this.imageInfo = null;
//        }
//    }
//
//    public byte[] getBytes() {
//        return bytes;
//    }
//
//    public ImageInfo getImageInfo() {
//        return imageInfo;
//    }
//
//    public Date getLastModified() {
//        return lastModified;
//    }
//}
