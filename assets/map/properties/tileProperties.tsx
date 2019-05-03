<?xml version="1.0" encoding="UTF-8"?>


<tileset version="1.2" tiledversion="1.2.3" name="drawingtest" tilewidth="80" tileheight="80" tilecount="108" columns="9">
 <image source="tileset.png" width="720" height="964"/>
 <tile id="0">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="1">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="2">
  <properties>
   <property name="type" value="hole"/>
   <property name="value" type="int" value="0"/>
  </properties>
 </tile>
 <tile id="3">
  <properties>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="gear"/>
  </properties>
 </tile>
 <tile id="4">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="5">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="7">
  <properties>
   <property name="direction" value="NORTH"/>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="8">
  <properties>
   <property name="direction" value="vertical"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="1"/>
  </properties>
 </tile>
 <tile id="9">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="10">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="11">
  <properties>
   <property name="name" value="normal"/>
   <property name="type" value="wrench"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="12">
  <properties>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="gear"/>
  </properties>
 </tile>
 <tile id="13">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="14">
  <properties>
   <property name="conveyorType" value="straight"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="16">
  <properties>
   <property name="direction" value="SOUTH"/>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="17">
  <properties>
   <property name="direction" value="horizontal"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="1"/>
  </properties>
 </tile>
 <tile id="18">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="19">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="20">
  <properties>
   <property name="name" value="hammer"/>
   <property name="type" value="wrench"/>
   <property name="value" type="int" value="-1"/>
  </properties>
 </tile>
 <tile id="21">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="22">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="23">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="25">
  <properties>
   <property name="direction" value=""/>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="26">
  <properties>
   <property name="direction" value="vertical"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="2"/>
  </properties>
 </tile>
 <tile id="27">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="28">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="29">
  <properties>
   <property name="name" value="flag1"/>
   <property name="type" value="wrench"/>
   <property name="value" type="int" value="1"/>
  </properties>
 </tile>
 <tile id="30">
  <properties>
   <property name="name" value="flag2"/>
   <property name="type" value="wrench"/>
   <property name="value" type="int" value="2"/>
  </properties>
 </tile>
 <tile id="31">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="32">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="34">
  <properties>
   <property name="direction" value="EAST"/>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="35">
  <properties>
   <property name="direction" value="horizontal"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="2"/>
  </properties>
 </tile>
 <tile id="36">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="37">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="38">
  <properties>
   <property name="name" value="flag3"/>
   <property name="type" value="wrench"/>
   <property name="value" type="int" value="3"/>
  </properties>
 </tile>
 <tile id="39">
  <properties>
   <property name="name" value="flag4"/>
   <property name="type" value="wrench"/>
   <property name="value" type="int" value="4"/>
  </properties>
 </tile>
 <tile id="40">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="41">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="43">
  <properties>
   <property name="direction" value="WEST"/>
   <property name="type" value="wall"/>
  </properties>
 </tile>
 <tile id="44">
  <properties>
   <property name="direction" value="vertical"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="3"/>
  </properties>
 </tile>
 <tile id="45">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="46">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="47">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="false"/>
   <property name="direction" value="EAST"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="48">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="false"/>
   <property name="direction" value="SOUTH"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="49">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="50">
  <properties>
   <property name="conveyorType" value="turn"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="53">
  <properties>
   <property name="direction" value="horizontal"/>
   <property name="type" value="laser"/>
   <property name="value" type="int" value="3"/>
  </properties>
 </tile>
 <tile id="54">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="55">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="56">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="false"/>
   <property name="direction" value="NORTH"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="57">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="false"/>
   <property name="direction" value="WEST"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="58">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="59">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="63">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="64">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="65">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="true"/>
   <property name="direction" value="EAST"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="66">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="true"/>
   <property name="direction" value="SOUTH"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="67">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="68">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="true"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="69">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="70">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="72">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="73">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="74">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="true"/>
   <property name="direction" value="NORTH"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="75">
  <properties>
   <property name="activatesOnEvenTurns" type="bool" value="true"/>
   <property name="direction" value="WEST"/>
   <property name="type" value="pusher"/>
  </properties>
 </tile>
 <tile id="76">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="77">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="78">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="79">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="4"/>
  </properties>
 </tile>
 <tile id="81">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="82">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="83">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="84">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="85">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="86">
  <properties>
   <property name="conveyorType" value="into"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="rotationDirection" type="bool" value="false"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="87">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="5"/>
  </properties>
 </tile>
 <tile id="88">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="6"/>
  </properties>
 </tile>
 <tile id="90">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="91">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="92">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="93">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="94">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="NORTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="2"/>
  </properties>
 </tile>
 <tile id="95">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="SOUTH"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="3"/>
  </properties>
 </tile>
 <tile id="96">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="7"/>
  </properties>
 </tile>
 <tile id="97">
  <properties>
   <property name="type" value="startingPoint"/>
   <property name="value" value="8"/>
  </properties>
 </tile>
 <tile id="99">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="EAST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="100">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="false"/>
   <property name="moveDirection" value="WEST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
 <tile id="101">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="102">
  <properties>
   <property name="type" value="normal"/>
  </properties>
 </tile>
 <tile id="103">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="EAST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="1"/>
  </properties>
 </tile>
 <tile id="104">
  <properties>
   <property name="conveyorType" value="combine"/>
   <property name="isExpress" type="bool" value="true"/>
   <property name="moveDirection" value="WEST"/>
   <property name="type" value="conveyorBelt"/>
   <property name="value" value="0"/>
  </properties>
 </tile>
</tileset>
