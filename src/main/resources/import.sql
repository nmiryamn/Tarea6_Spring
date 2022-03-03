INSERT INTO pelicula(duration, summary, title, year) VALUES ('1h 54m','Tras desatar una antigua guerra, el codicioso guerrero Thor es desterrado a la Tierra por su padre para que viva entre los hombres y descubra así el verdadero sentido de la humildad. Allí, sin sus poderes, Thor deberá enfrentarse a las fuerzas más oscuras que su mayor enemigo le enviará desde Asgard.','Thor',2011); 
INSERT INTO pelicula(duration, summary, title, year) VALUES ('2h 23m','Con la inminente destrucción de Krypton, Jor-El y su mujer buscan una manera de preservar su raza y envían a su hijo a la Tierra para que viva entre los humanos. El bebé llega a una granja de Kansas, donde es criado en los valores de sus padres adoptivos, Martha y Jonathan Kent.','El hombre de acero',2013); 

INSERT INTO actor(birthdate, name, nationality, surname) VALUES ('1981-09-02','TOM','INGLÉS','HIDDLESTON'); 
INSERT INTO actor(birthdate, name, nationality, surname) VALUES ('1983-05-05','HENRY','INGLÉS','CAVILL'); 

INSERT INTO actor_pelicula(pelicula_id, actor_id) VALUES (1,1);
INSERT INTO actor_pelicula(pelicula_id, actor_id) VALUES (2,2);