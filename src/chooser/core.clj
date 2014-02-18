(ns chooser.core
  (:import [javax.swing JPanel JLabel JFrame])
  (:use [tawny.owl]
        [tawny.reasoner]
        [pizza.pizza]
        [pizza.render-pizza])
  (:gen-class))

(defn chooserWindow []
	(let [label (JLabel. "Stuff")
		  labelb (JLabel. "otherStuff")	
		  panel (JPanel.)
		  frame (JFrame. "chooser")]
		  (.setOpaque panel true)
		  (.setText label (.toString PepperTopping))
		  (.setText labelb (.toString InterestingPizza))
		  (.add panel label)
		  (.add panel labelb)
		  (.setContentPane frame panel)
		  (.setSize frame 300 100)
		  (.setVisible frame true)))

;; so need to get reasoner working
;; fetching subclasses
(tawny.reasoner/isubclasses pizza.pizza/pizzaontology PepperTopping)

;; find annotation property called "label"
