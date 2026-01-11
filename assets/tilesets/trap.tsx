<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.2" name="trap" tilewidth="128" tileheight="128" tilecount="6" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="0">
  <properties>
   <property name="type" value="hazard"/>
  </properties>
  <image source="../sprites/Details/trap1.png" width="128" height="128"/>
 </tile>
 <tile id="1">
  <image source="../sprites/Details/trap2.png" width="128" height="128"/>
  <animation>
   <frame tileid="1" duration="100"/>
   <frame tileid="2" duration="100"/>
   <frame tileid="3" duration="100"/>
   <frame tileid="4" duration="100"/>
   <frame tileid="0" duration="100"/>
  </animation>
 </tile>
 <tile id="2">
  <image source="../sprites/Details/trap3.png" width="128" height="128"/>
 </tile>
 <tile id="3">
  <image source="../sprites/Details/trap4.png" width="128" height="128"/>
 </tile>
 <tile id="4">
  <image source="../sprites/Details/trap5.png" width="128" height="128"/>
 </tile>
 <tile id="5">
  <image source="../sprites/Details/trap6.png" width="128" height="128"/>
 </tile>
</tileset>
