//
//  FoodViewFunctions.swift
//  assignment_3
//
//  Created by Samuel Li on 13/5/21.
//

import Foundation
import SwiftUI
import RealmSwift

class FoodViewFunctions: ObservableObject {
    @Published var foodName = ""
    @Published var calrious = ""
    @Published var servings = "1"
    @Published var date : Date = Date()
    // page activity
    @Published var openPage2 = false

    // stores data
    @Published var foods: [Food] = [] //Foods from today
    @Published var foodsAll: [Food] = [] //Foods from all days
    @Published var history: [History] = [] //Intake history
    @Published var updateFood: Food?
    @Published var totalCalories = 0
    @Published var targetCalories = "2500"
    
    //Date > String Converter
    let dateFormatter = DateFormatter()
    
    init(){
        getFoods()
        if (foodsAll != []) {
            getHistory()
        }
    }
    
    let realm = try! Realm()
    // add food 
    func addManual(presentation:Binding<PresentationMode>){
        //Set formatter style
        dateFormatter.dateStyle = .short
        //Remove timestamp from date
        date.removeTimeStamp
        
        let food = Food()
        food.foodName = foodName
        food.calrious = calrious
        food.servings = servings
        food.date = dateFormatter.string(from: date)
        try! realm.write {
        realm.add(food)
        }
        getFoods()
        getHistory()
        //Reset current values in add function
        foodName = ""
        calrious = ""
        servings = "1"
        date = Date()
        presentation.wrappedValue.dismiss()
    }
    
    func deleteFood(entry:Food){
        try? realm.write{
            realm.delete(entry)
            getFoods()
            getHistory()
        }
    }
 
    //Get foods on current day
    func getFoods(){
        //Set formatter style
        dateFormatter.dateStyle = .short
        
        guard let realm = try? Realm() else {return}
        let items = realm.objects(Food.self)
        //Array of foods from today's date
        let filteredItems = items.filter{ [self] in $0.date == dateFormatter.string(from: Date())}
        // return to UI
        self.foods = filteredItems.compactMap({(food)-> Food? in return food})
        
        totalCalories = calculateCalrious()
     
    }
    
    //Get foods across all days
    func getFoodsAll(){
        guard let realm = try? Realm() else {return}
        let items = realm.objects(Food.self)
        //Sort by date descending order
        let sortedItems = items.sorted{$0.date.compare($1.date) == .orderedDescending}
        // return to UI
        self.foodsAll = sortedItems.compactMap({(food)-> Food? in return food})
    }
    
    func getHistory() {
        //Reset array whenever function is called
        history = []
        //Array for each seperate date
        var dateArr = [[Food]]()
        //Temp arr for elements in a single date
        var tempArr = [Food]()
        //Update all foods
        getFoodsAll()
        
        let startingDate = foodsAll[0].date
        var currentDate = startingDate
        var previousDate = startingDate

        //Adds foods to a temporary array
        for food in foodsAll {
            currentDate = food.date
            if (currentDate == previousDate) {
                tempArr.append(food)
            }
            else {
                //Appends the food array to another array
                dateArr.append(tempArr)
                //Reset temp array whenever a new date is detected
                tempArr = []
                tempArr.append(food)
            }
            previousDate = currentDate
        }
        //To add final date
        dateArr.append(tempArr)
        
        //Creates a history object for each day in the dateArr
        for day in dateArr {
            let dayHistory = History()
            
            let date = day[0].date
            let dailyCalories = calculateCaloriesDay(day: day)
            let remainder = Int(targetCalories)! - dailyCalories
            
            dayHistory.date = date
            dayHistory.totalCalories = String(dailyCalories)
            dayHistory.remainder = String(remainder)
            
            history.append(dayHistory)
        }
    }
    
    //Calculate total calories for current day
    func calculateCalrious()->Int{
        var result = 0
        for item in foods {
            let convert = Int(item.calrious)
            let convertServings = Int(item.servings)
                result += convert! * convertServings!
        
        }
       
        return result
        
    }
    
    //Calculate total calories for selected day
    func calculateCaloriesDay(day: [Food])->Int{
        var result = 0
        for item in day {
            let convertCalories = Int(item.calrious)!
            let convertServings = Int(item.servings)!
            result += convertCalories * convertServings
        }
        return result
    }
    
    //Set target calories
    func setTarget(presentation:Binding<PresentationMode>, target: String) {
        targetCalories = target
        presentation.wrappedValue.dismiss()
    }
    
    // updating data
    func setUpInitData(){
        guard let updateData = updateFood else {return}
        foodName = updateData.foodName
        calrious = updateData.calrious
    }
    func defInitData(){
        updateFood = nil
        foodName = ""
        calrious = ""
    }
    
    
    
}
