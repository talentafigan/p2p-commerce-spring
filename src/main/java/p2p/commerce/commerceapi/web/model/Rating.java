package p2p.commerce.commerceapi.web.model;

import javax.persistence.*;

@Entity
@Table(name = "reting")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reting_id")
    private int ratingId;

    @Column(name = "reting")
    private int reting;

    @Column(name = "rating_desctiption")
    private int ratingDesctiption;
}
