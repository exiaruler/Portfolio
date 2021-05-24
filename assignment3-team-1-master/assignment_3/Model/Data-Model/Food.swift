//
//  Food.swift
//  assignment_3
//
//  Created by Samuel Li on 5/5/21.
//

import Foundation
import RealmSwift
// Realm food model
class Food:Object,Identifiable {
    @objc dynamic var _id: ObjectId = ObjectId.generate()
    @objc dynamic var foodName = ""
    @objc dynamic var calrious  = ""
    @objc dynamic var date = "\(Date())"
    @objc dynamic var servings = ""
    override static func primaryKey() -> String? {
        return "_id"
    }
    
}

