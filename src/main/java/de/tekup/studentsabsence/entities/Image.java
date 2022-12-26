package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
	public Image(Object object, String fileName2, String fileType2, byte[] data2) {
		// TODO Auto-generated constructor stub
	}
	public String getFileType() {
		// TODO Auto-generated method stub
		return null;
	}
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
