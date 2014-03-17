(ns chooser.core
  (:import  [javax.swing JPanel JLabel JFrame JButton JOptionPane JComboBox]
			[java.awt.event ActionListener]
			)
  
  (:use 	[tawny.owl]
        	[tawny.reasoner]
        	[pizza.pizza]
       		[pizza.render-pizza]
       		)
  
  (:require [tawny
            [read]
            [polyglot]
            [reasoner :as r]
            [pattern :as p]]
    		[seesaw.core :as sc]
    		[seesaw.dev :as sd]
            )
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

		  		 )
		  drop-chooser (JComboBox. )]

		  (.setOpaque panel true)
          (.add panel button)
          (.add panel drop-chooser)
		  (.setContentPane frame panel)
		  (.setSize frame 400 300)
		  (.setVisible frame true)
	)
)
(defn seesaw-chooser []
	(r/reasoner-factory :elk)
	)

(def title "Pizza Things")
(def window-size {:width 1000
                  :height 800})
(def lb (sc/combobox :model (-> 'seesaw.core ns-publics keys sort)))
(def lb2 (sc/combobox :model (-> 'seesaw.core ns-publics keys sort)))
;(def cb (sc/listbox :model (-> 'tawny.reasoner/isubclasses pizza.pizza/pizzaontology)))
(def txtArea (sc/text :multi-line? true :font "MONOSPACED-PLAIN-14"
				:text
				"This
				is 
				multi
				line 
				text" ))
(defn doc-str [s] (-> (symbol "seesaw.core" (name s)) resolve meta :doc))

(sc/listen lb :selection
        (fn [e]
          (when-let [s (sc/selection e)]
            (-> txtArea
              (sc/text!   (doc-str s))
              (sc/scroll! :to :top)))))

(defn main-window []
  (r/reasoner-factory :elk)
  (->(sc/frame :title title
        :width (:width window-size)
        :height (:height window-size)
        :content (sc/border-panel
          :west (sc/border-panel 
              :north lb 
              :south lb2)	  							 
        	:east txtArea
        	:vgap 5 :hgap 5 :border 5)
        )
    sc/show!
     )         
  
  	
  )

;; so need to get reasoner working
;; fetching subclasses
;(tawny.reasoner/isubclasses pizza.pizza/pizzaontology PepperTopping)

;; find annotation property called "label"
