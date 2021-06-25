(ns foo.user
  (:require
   [foo.sql :as sql]))

(sql/defsql "foo/queries.sql" :as q)

(defn followings
  [db-ro id page-number page-size]
  (q/followings db-ro {:offset    (* page-size (- page-number 1))
                       :page-size page-size
                       :user-id   id}))
