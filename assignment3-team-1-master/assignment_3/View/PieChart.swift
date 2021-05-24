//
//  Personal.swift
//  assignment_3
//
//  Created by user182748 on 5/8/21.
//

import Charts
import SwiftUI

struct PieChart: UIViewRepresentable {
    
    //@EnvironmentObject var storedFoods : FoodViewFunctions
    
    var entries: [PieChartDataEntry] = entriesForFood(foods: foodModel.foods)
    
    let pieChart = PieChartView();
    
    func makeUIView(context: Context) -> PieChartView {
        pieChart.delegate = context.coordinator
        return pieChart
    }
    
    //Plots data for the PieChart using the entries
    func updateUIView(_ uiView: PieChartView, context: Context) {
        let dataSet = PieChartDataSet(entries: entries)
        dataSet.colors = ChartColorTemplates.material()
        let pieChartData = PieChartData(dataSet: dataSet)
        uiView.data = pieChartData
        
        configuareChart(uiView)
        formaChartCentre(uiView)
        formatLegend(uiView.legend)
        
        uiView.notifyDataSetChanged()
        
    }
    
    class Coordinator: NSObject, ChartViewDelegate {
        var parent: PieChart
        init(parent: PieChart) {
            self.parent = parent
        }
    }
    
    func makeCoordinator() -> Coordinator {
       Coordinator(parent: self)
    }
    
    func configuareChart(_ pieChart: PieChartView){
        pieChart.rotationEnabled = false
    }
    
    func formaChartCentre(_ pieChart: PieChartView) {
        pieChart.holeColor = UIColor.systemBackground
    }
    
    
    func formatLegend(_ legend: Legend) {
        legend.enabled = false
    }
    
     
}

var foodModel = FoodViewFunctions()

//Getting entries from food model and return them as data entries
func entriesForFood(foods: [Food]) -> [PieChartDataEntry] {
    let requestData = foods
    return requestData.map {PieChartDataEntry(
        value: Double($0.calrious)! * Double($0.servings)!,
        label: $0.foodName
    )}
}







