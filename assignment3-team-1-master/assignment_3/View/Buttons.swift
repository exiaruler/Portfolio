//
//  Buttons.swift
//  assignment_3
//
//  Created by user924970 on 5/16/21.
//

import SwiftUI

//Button Presets
//Use with .buttonStyle(ButtonName())

struct Buttons: View {
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct SmallOutlineButton: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration
            .label
            .font(Font.footnote.weight(.bold))
            .foregroundColor(.green)
            .padding(5)
            .background(
                RoundedRectangle(
                    cornerRadius: 8,
                    style: .continuous
                ).stroke(Color.green)
            )
    }
}

struct StandardFilledButton: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration
            .label
            .foregroundColor(.white)
            .padding(15)
            .background(Color.green)
            .cornerRadius(8.0)
            .clipShape(Capsule())
            .font(Font.headline.weight(.bold))
    }
}





//struct Buttons_Previews: PreviewProvider {
//    static var previews: some View {
//        Buttons()
//    }
//}
