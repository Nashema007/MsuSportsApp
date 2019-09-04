<?php
	/**
	 * Created by PhpStorm.
	 * User: Dell
	 * Date: 3/28/2019
	 * Time: 5:48 PM
	 */
	   require "../model/UpdatesModel.php";
	   require "DbConnector.php";
	   
	class SportEventsUpdates {
		
		function retrieveSoccer(){
			
			//$soccerModal = new UpdatesModel();
			$con = new DbConnector();
			$conn = $con -> con();
			
			
			$titles = $conn->query("SELECT * FROM soccer_updates");
			$values = array();
			while ($row = mysqli_fetch_array($titles))    {
				
				array_push($values,array(
					"id"=>$row['id'],
					"title"=>$row['update_title'],
					"description"=>$row['update_description']));
			}
			
			 return $values;
		}
		function retrieveBasketBall(){
			$con = new DbConnector();
			$conn = $con -> con();
			
			
			$titles = $conn->query("SELECT * FROM basketball_updates");
			$values = array();
			while ($row = mysqli_fetch_array($titles))    {
				
				array_push($values,array(
					"id"=>$row['id'],
					"title"=>$row['update_title'],
					"description"=>$row['update_description']));
			}
			
			return $values;
		}
		function retrieveHockey(){
			$con = new DbConnector();
			$conn = $con -> con();
			
			
			$titles = $conn->query("SELECT * FROM hockey_updates");
			$values = array();
			while ($row = mysqli_fetch_array($titles))    {
				
				array_push($values,array(
					"id"=>$row['id'],
					"title"=>$row['update_title'],
					"description"=>$row['update_description']));
			}
			
			return $values;
		}
		function retrieveEvents(){
			$con = new DbConnector();
			$conn = $con -> con();
			
			$titles = $conn->query("SELECT * FROM event_updates");
			$values = array();
			
			while ($row = mysqli_fetch_array($titles))    {
				
				array_push($values,array(
					"id"=>$row['id'],
					"title"=>$row['event_title'],
					"description"=>$row['event_description']));
			}
			return $values;
			
		}
		
		
		
	}