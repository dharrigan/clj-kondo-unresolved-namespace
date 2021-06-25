(ns foo.sql)

(defmacro defsql
  [& args]
  (let [files (vec (drop-last 2 args))
        alias (last args)]
    `(let [current-namespace# *ns*
           query-namespace#   (symbol (str (ns-name *ns*) ".sql"))]
       (create-ns query-namespace#)
       (in-ns query-namespace#)
       (require '[hugsql.core :as hugsql])
       (doseq [file# ~files]
         (hugsql/def-db-fns file# {:command-options [{:identifiers identity}]}))
       (in-ns (ns-name current-namespace#))
       (alias '~alias query-namespace#))))
