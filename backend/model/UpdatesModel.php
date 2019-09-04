<?php
	/**
	 * Created by PhpStorm.
	 * User: Dell
	 * Date: 3/28/2019
	 * Time: 5:41 PM
	 */
	
	class UpdatesModel {
	
	private $title;
	private $summary;
		
		/**
		 * @return mixed
		 */
		public function getTitle() {
			return $this->title;
		}
		
		/**
		 * @param mixed $title
		 */
		public function setTitle($title): void {
			$this->title = $title;
		}
		
		/**
		 * @return mixed
		 */
		public function getSummary() {
			return $this->summary;
		}
		
		/**
		 * @param mixed $summary
		 */
		public function setSummary($summary): void {
			$this->summary = $summary;
		}
		
		
		
		
		
	}