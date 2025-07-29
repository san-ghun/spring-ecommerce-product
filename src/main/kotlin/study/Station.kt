package study

import jakarta.persistence.*

@Entity // (1)
@Table(name = "station") // (2)
class Station(
    @Column(name = "name", nullable = false) // (3)
    var name: String,
    
    @Id // (4)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (5)
    val id: Long = 0L,
) {
    fun changeName(name: String) {
        this.name = name
    }
}

/**
 * IF without the plugin, kotlin("plugin.jpa") version "1.9.25"
 * write like below
 *
 * (1) @Entity
 * Marks the class as a JPA entity and maps it to a database table.
 *
 * (2) @Table
 * Specifies the table name to which the entity is mapped.
 * If omitted, the table name defaults to the class name.
 * Declaring it is optional.
 *
 * (3) @Column
 * Maps the field to a specific column in the table using the given name.
 * This annotation is optional if the column name matches the field name.
 *
 * (4) @Id
 * Specifies the primary key field of the entity.
 *
 * (5) @GeneratedValue
 * Defines how the primary key should be generated (e.g., auto-increment).
 *
 * (6) No-argument constructor
 * The entity class must have a no-arg constructor.
 * It may also define other constructors. — JSR 338
 */
//@Entity // (1)
//@Table(name = "station") // (2)
//open class Station(
//    @Column(name = "name", nullable = false) // (3)
//    var name: String,
//
//    @Id // (4)
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // (5)
//    val id: Long = 0L,
//) {
//    constructor(): this("", null) // (6)
//}
