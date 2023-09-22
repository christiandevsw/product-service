insert into categories(name,description) values('Postre','Helados,cupcackes y tortas');
insert into categories(name,description) values('Filete','Lonjas anchas y doradas de las rodajas. Tenemos filetes de carne y pollo');
insert into categories(name,description) values('Hamburguesa','Hamburguesas doble porción, tradicional, para veganos y más ... ');
insert into categories(name,description) values('Pizza','Una pizza que te recordara a la Velha Italia');
insert into categories(name,description) values('Café','Acompañe su desayuno con un cafe capuchino, americano o un macchiato');
----------------------------

insert into products(name,price,dscto,category_id,description,stock,available) values ('Suspiro de limeña',12,0,1,'Manjar real del Perú, con dulce de leche y merengue',30,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Alfajor',8.30,0,1,'Masa bañado en dulce de leche',40,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Mazamorra morada',9.50,0,1,'Maiz morado, menbrillo, manzana y piña',30,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Picarones',15.50,0,1,'Harinas de trigo crocantes',40,false);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Arroz con leche',8.30,0,1,'Sabrosa para el paladar más exigente',50,true);

insert into products(name,price,dscto,category_id,description,stock,available) values ('Bife de cerdo parrillero con anticucho',25.90,0.05,2,'1 Bife de cerdo + 1 porción de arroz con choclo + 1 porción de papas parrilleras + 1 palito de anticucho',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Bife Angosto',38.90,0.05,2,'1 Bife Angosto + 2 tajadas de plátanos fritos + 1 Ensalada Fresca + 1 Porción de Papas Parrilleras',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Carne a lo pobre',29.90,0.05,2,'1 Bisteck + 1 porción de papas fritas + 1 porción de arroz con choclo + 2 tajadas de plátanos fritos + 1 huevo frito',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Pechuga parrillera con chorizo',23.90,0.05,2,'1 Pechuga criolla + 1 porción de arroz con choclo + 1 porción de papas fritas + 1 chorizo parrillero.',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Churrasco',32.90,0.05,2,'1 Churrasco + 2 tajadas de plátanos fritos + 1 Ensalada Fresca + 1 Porción de Papas Parrilleras',50,true);

insert into products(name,price,dscto,category_id,description,stock,available) values ('Hamburguesa artesanal',23.90,0.05,3,'Angus con queso, acompañada de tomate y lechuga',30,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Surf and turf burguer',36.80,0.05,3,'hamburguesa de carne Americana acompañada de langostino jumbo, champiñones, tocino y queso crema',20,false);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Triple bacon Stackhouse burger',39.90,0.05,3,'Hamburguesa a la parrilla gratinada con queso Cheddar y acompañada de tres tipos de tozino',50,false);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Burger Italiano',25.30,0.05,3,'Carne de res Angus, tomates, arúgula, queso gorgonzola, cebolla confitada',30,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Kobe truffle burger',25.30,0.05,3,'Molienda e Wagyu, alioli de Trufa, cheddar blanco y cebolla caramelizada',30,true);

insert into products(name,price,dscto,category_id,description,stock,available) values ('Pizza Mozzarella',26.90,0,4,'Con doble queso mozzarella',40,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Pizza Pepperoni',26.90,0,4,'Con queso mozzarella y pepperoni',40,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Pizza Vegetariana',26.90,0,4,'Con queso mozzarella, champiñones, aceituans negras, etc',40,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Pizza Hawaiana',31.90,0,4,'Con queso mozzarella, jamon, piña, etc',40,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Pizza Hawaiana',31.90,0,4,'Con queso mozzarella, chorizo, champiñones, aceitunas verdes y aceitunas negras',40,true);

insert into products(name,price,dscto,category_id,description,stock,available) values ('Café Americano',8.50,0,5,'Un Expreso más suave y dulce',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Café Capuchino',9.60,0,5,'Expreso con espuma de leche',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Café Expresso',9.00,0,5,'Café al paso',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Café Ristretto',9.30,0,5,'Café concentrado',50,true);
insert into products(name,price,dscto,category_id,description,stock,available) values ('Café Machiatto',9.50,0,5,'Café con tinte ligero de leche',50,true);

----------------------------
-- insert into reservations(date) values ('2023-09-05 12:30:12');
-- insert into reservations(date) values ('2023-09-07 12:45:00');
-- insert into reservations(date) values ('2023-09-08 13:34:23');
----------------------------
-- insert into customers(dni,name,last_name,email,phone,address) values ('73455249','Juan','Ramos Torres','juanramos@gmail.com','934342382','Surco');
-- insert into customers(dni,name,last_name,email,phone,address) values ('73453434','Mario Carlos Andres','Sotelo Valcarcel','mcandres@gmail.com','942382343','Surco');
-- insert into customers(dni,name,last_name,email,phone,address) values ('54545549','Marco','Peniche Gomez','marco_gomez@gmail.com','903433332','Surco');
-- insert into customers(dni,name,last_name,email,phone,address) values ('88848449','Susan','Escajadillo Lopez','susaneslopes@gmail.com','999934222','Chorrillos');
-- insert into customers(dni,name,last_name,email,phone,address) values ('89449574','Carlo','Sosa Martinez','carlo.sosa.mart@gmail.com','934342382','Lima');
-- insert into customers(dni,name,last_name,email,phone,address) values ('45454549','Floricienta','Rodriguez Dalia','flor_dalia@gmail.com','944443821','Lima');
-- insert into customers(dni,name,last_name,email,phone,address) values ('54545524','Edgardo','Benites Alfaro','edgardo.300@gmail.com','977773454','Surquillo');
-- insert into customers(dni,name,last_name,email,phone,address) values ('66666624','Michel','Amparo Torres','kornmichel23@gmail.com','934346635','Miraflores');
-- insert into customers(dni,name,last_name,email,phone,address) values ('89954754','Vanessa','Smit Gomez','vanessa.smit@gmail.com','934342092','Surco');
-- insert into customers(dni,name,last_name,email,phone,address) values ('44567777','Juan','Alvites Sarandom','juan.sarandom@gmail.com','934004543','Surco');
-- insert into customers(dni,name,last_name,email,phone,address) values ('77777333','Jim','Ford Carrie','jimforcarrie@gmail.com','934746464','Surco');
