(defproject io.clojure/async-workshop "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-alpha2"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [org.clojure/data.json "0.2.5"]
                 [com.cemerick/friend "0.2.1" :exclusions [org.clojure/core.cache]]
                 [compojure "1.1.9"]
                 [enlive "1.1.5"]
                 [http-kit "2.1.16"]
                 [javax.servlet/servlet-api "2.5"]
                 [om "0.7.3"]
                 [ring/ring-core "1.3.1"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :source-paths ["src/clj" "src/chat-demo" "src/channel-demo"]
  :resource-paths ["resources"]
  :main async-workshop.server
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds [{:id "channel-demo"
                        :source-paths ["src/channel-demo"]
                        :compiler {:output-to "target/classes/public/components/async-workshop-channel-demo/async-workshop-channel-demo.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]}
  :profiles {:dev {:source-paths ["devel/clj" "dev/cljs"]
                   :dependencies [[ring/ring-devel "1.3.1"]]
                   :plugins [[com.cemerick/austin "0.1.5"]]
                   :cljsbuild {:builds [{:id "chat-demo"
                                         :source-paths ["src/chat-demo"]
                                         :compiler {:output-to "target/classes/public/js/chat-demo.js"
                                                    :output-dir "target/classes/public/js/chat-demo"
                                                    :optimizations :none
                                                    :source-map true}}]}
                   :repl-options {:timeout 120000
                                  :init-ns user
                                  :init (start)
                                  :welcome (do
                                             (println "Welcome to the core.async tutorial!")
                                             (println "Please browse to http://localhost:9000 to get started."))}}
             :metadev {:cljsbuild {:builds [{:id "channel-demo"
                                             :source-paths ["src/channel-demo" "devel/cljs"]
                                             :compiler {:output-to "target/classes/public/components/async-workshop-channel-demo/async-workshop-channel-demo.js"
                                                        :output-dir "target/classes/public/components/async-workshop-channel-demo/out"
                                                        :optimizations :none
                                                        :source-map true}}]}}
             :uberjar {:aot :all}})
