(defproject chooser "0.1.0-SNAPSHOT"
  :description "pizza-chooser"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				[uk.org.russet/tawny-owl "1.0"]
  				[pizza "1.0.0-SNAPSHOT"]]
  :main ^:skip-aot chooser.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
