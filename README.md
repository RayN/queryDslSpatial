Testing queryDsl for spatial queries using Postgis <br/>

QueryDSL has good support for spatial queries but poor documentation.<br/>
That's why I shared this project.<br/><br/>


# Features
- Union using st_union
- Intersection with st_intersection
- Intersects with st_intersects


# Running the project
1 - Clone the project <br/>
2 - Create a database named 'queryDslSpatial'. Or any other you like, just change application.properties <br/>
3 - Install postgis in the database: https://postgis.net/install/ <br/>
4 - Start QueryDslSpatialTestApplication as a Spring Boot App. The tables and data will be inserted automatically using flyway <br/>


# Caveats
I had to override com.querydsl.sql.spatial.SpatialTemplatesSupport from queryDsl. <br/>

Replaced 

		ops.put(SpatialOps.UNION, createSpatial(prefix + "Union", 2, asFunction));

With

		ops.put(SpatialOps.UNION, createSpatial(prefix + "Union", 1, asFunction));


