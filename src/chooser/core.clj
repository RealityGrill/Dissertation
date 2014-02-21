(ns chooser.core
  (:import [javax.swing JPanel JLabel JFrame JButton])
  (:use [tawny.owl]
        [tawny.reasoner]
        [pizza.pizza]
        [pizza.render-pizza])
  (:require [tawny
             [read]
             [polyglot]
             [reasoner :as r]
             [pattern :as p]])
  (:gen-class))

(defn chooserWindow []
	(r/reasoner-factory :elk)
	(let [panel (JPanel.)
		  frame (JFrame. "chooser")
		  button (JButton. "button")]
		  (.setOpaque panel true)
          (.add panel button)
                 (doseq [n 
                          (tawny.reasoner/isubclasses pizza.pizza/pizzaontology 
                                                      PepperTopping)]
                    (.add panel
                          (JLabel. (.toString n))))
		  (.setContentPane frame panel)
		  (.setSize frame 400 300)
		  (.setVisible frame true)))

;; so need to get reasoner working
;; fetching subclasses
;(tawny.reasoner/isubclasses pizza.pizza/pizzaontology PepperTopping)

;; find annotation property called "label"
