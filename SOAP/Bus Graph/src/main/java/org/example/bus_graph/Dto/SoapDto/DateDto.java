package org.example.bus_graph.Dto.SoapDto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement(name="DateDto")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor
public class DateDto {

    private Integer year;
    private Integer month;
    private Integer day;
    @Override
    public String toString() {
        return "DateDto{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
