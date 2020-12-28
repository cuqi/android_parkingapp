# android_parkingapp
Reserve parking in major cities in the US.

-- About -- 

Апликација за резервирање на паркинзи во големите градови во Америка. 

-- Features -- 

 - логирање на постоечки корисник
 - регистрација на нов корисник 
 - activity за избор на град со RecyclerView
 - activity за избор на датум и временски интервал за паркингот (layout fragments)
 - activity за избор на паркинг во рамки на град (update на број на слободни паркинг места)
 - activity за навигација со дирекции од почетна локација(30.30458636573813, -97.70019787616641) до дестинацискиот паркинг
 - toolbar со резервации по user, QR code за секоја резервација
 - скриен API_KEY во build.gradle
 
 -- Database -- 
 
 User_table(id*, first_name, last_name, username, password)
 Parking_table(id*, parking_name, city_name, free, taken, x, y)
 Reservation_table(*id, user_id*, parking_id*, time_slot, date)
