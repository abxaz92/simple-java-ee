package local.diplom.service.model;

import javax.persistence.*;

/**
 * Created by david on 14.05.16.
 */
@Entity
public class Image {
    private Long id;
    private String mimeType;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    protected byte[] imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }
}
