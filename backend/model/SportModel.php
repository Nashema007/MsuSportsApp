<?php

	
	class SportModel {
	
		private $soocer;
		private $basketball;
		private $hockey;
		
		/**
		 * @return mixed
		 */
		public function getSoocer() {
			return $this->soocer;
		}
		
		/**
		 * @param mixed $soocer
		 */
		public function setSoocer($soocer): void {
			$this->soocer = $soocer;
		}
		
		/**
		 * @return mixed
		 */
		public function getBasketball() {
			return $this->basketball;
		}
		
		/**
		 * @param mixed $basketball
		 */
		public function setBasketball($basketball): void {
			$this->basketball = $basketball;
		}
		
		/**
		 * @return mixed
		 */
		public function getHockey() {
			return $this->hockey;
		}
		
		/**
		 * @param mixed $hockey
		 */
		public function setHockey($hockey): void {
			$this->hockey = $hockey;
		}
		
		
	
		
	}