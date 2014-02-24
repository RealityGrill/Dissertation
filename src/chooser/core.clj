(ns chooser.core
  (:import  [javax.swing JPanel JLabel JFrame JButton JOptionPane]
			[java.awt.event ActionListener])
  
  (:use 	[tawny.owl]
        	[tawny.reasoner]
        	[pizza.pizza]
       		[pizza.render-pizza])
  
  (:require [tawny
            [read]
            [polyglot]
            [reasoner :as r]
            [pattern :as p]])
  (:gen-class))

;;define a macro to define action listeners
(defmacro on-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn chooserWindow []
	(r/reasoner-factory :elk)
	(let [panel (JPanel.)
		  frame (JFrame. "chooser")
		  button (doto (JButton. "PepperPizza")
		  			(on-action event
		  				(doseq [n
		  						(tawny.reasoner/isubclasses pizza.pizza/pizzaontology PepperTopping)]
		  						(.add panel
		  							(JLabel. (.toString n))
		  						)
		  						(.revalidate panel)
		  				)
		  			)

		  		 )]
		  (.setOpaque panel true)
          (.add panel button)
                 ;(doseq [n 
                 ;         (tawny.reasoner/isubclasses pizza.pizza/pizzaontology 
                 ;                                     PepperTopping)]
                 ;   (.add panel
                 ;         (JLabel. (.toString n))))
		  
		  (.setContentPane frame panel)
		  (.setSize frame 400 300)
		  (.setVisible frame true)
	)
)

;;try creating new frame / panel. 


;; so need to get reasoner working
;; fetching subclasses
;(tawny.reasoner/isubclasses pizza.pizza/pizzaontology PepperTopping)

;; find annotation property called "label"
