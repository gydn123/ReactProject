package com.exciting.entity;

import javax.persistence.*;

//import com.exciting.dto.AmusementAimageDTO;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "amusement")
@Data
//@NamedNativeQueries({
//	@NamedNativeQuery(
//			name = "AmusementDTO.selectListAmuseInfoKorea",
//			query = "SELECT am.amuse_id as id, am.a_name, am.a_country, am.a_time, ai.url "
//					+ "FROM amusement AS am INNER JOIN aimage AS ai ON am.amuse_id = ai.amuse_id "
//					+ "WHERE am.a_country = '대한민국' "
//					+ "GROUP BY am.amuse_id "
//					+ "ORDER BY RAND() LIMIT 4",
//			resultSetMapping = "Amusement.AmusementAimageMapping"
//	),
//	@NamedNativeQuery(
//            name = "AmusementDTO.selectListAmuseInfoForeign",
//            query = "SELECT am.amuse_id as id, am.a_name, am.a_country, am.a_time, ai.url " +
//                    "FROM amusement AS am INNER JOIN aimage AS ai ON am.amuse_id = ai.amuse_id " +
//                    "WHERE am.a_country != '대한민국' " +
//                    "GROUP BY am.amuse_id " +
//                    "ORDER BY RAND() LIMIT 4",
//            resultSetMapping = "Amusement.AmusementAimageMapping"
//    ),
//	
//})
//@SqlResultSetMapping(
//		name = "Amusement.AmusementAimageMapping",
//		classes = @ConstructorResult(
//				targetClass = AmusementAimageDTO.class,
//				columns = {
//						@ColumnResult(name = "id", type = int.class),
//						@ColumnResult(name = "a_name", type = String.class),
//						@ColumnResult(name = "a_country", type = String.class),
//						@ColumnResult(name = "a_time", type = String.class),
//						@ColumnResult(name = "url", type = String.class)
//				}
//		)
//)
public class AmusementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amuse_id")
    private Integer amuse_id;

    @Column(name = "a_name", nullable = false, length = 100)
    private String a_name;

    @Column(name = "a_country", nullable = false, length = 45)
    private String a_country;

    @Column(name = "a_time", length = 45)
    private String a_time;

    @Column(name = "a_img", length = 300)
    private String a_img;

    @Column(name = "a_opening")
    private Date a_opening;

    @Column(name = "a_view", nullable = false)
    private Integer a_view;

    @Column(name = "a_info", length = 500)
    private String a_info;

    @Column(name = "a_lat")
    private Double a_lat;

    @Column(name = "a_lng")
    private Double a_lng;

    @Column(name = "review_cnt")
    private Integer review_cnt;

    @Column(name = "avg_grade")
    private Float avg_grade;

    @Column(name = "a_note", length = 45)
    private String a_note;

}
