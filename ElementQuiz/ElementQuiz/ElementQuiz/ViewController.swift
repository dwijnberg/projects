//
//  ViewController.swift
//  ElementQuiz
//
//  Created by Duncan Wijnberg on 11/11/22.
//
//Extension: Adds extra flashcards if you get an answer wrong on the quiz

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    enum Mode {
        case flashCard
        case quiz
    }
    enum State {
        
        case question
        case answer
        case score
        
    }
    
    var elementList : [String] = []
    var wrongAnswer = [
        "Carbon" : 0,
        "Sodium" : 0,
        "Chlorine" : 0,
        "Gold" : 0
    ]
    let fixedElementList = ["Carbon","Gold","Chlorine","Sodium"]
    var currentElementIndex = 0
    var mode : Mode = .flashCard {
        didSet {
            switch mode {
                
            case .flashCard: setupFlashcards()
            case .quiz: setupQuizzes()
                
            }
            updateUI()
        }
        
    }
    var state : State = .question
    var answerIsCorrect = false
    var correctAnswerCount = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mode = .flashCard
        // Do any additional setup after loading the view.
    }
    
    @IBOutlet weak var imageView: UIImageView!
    
    @IBOutlet weak var answerLabel: UILabel!
    
    @IBAction func showAnswer(_ sender: Any) {
        state = .answer
        updateUI()
    }
    @IBAction func next(_ sender: Any) {
        currentElementIndex += 1
        if currentElementIndex >= elementList.count {
            
            currentElementIndex = 0
            if mode == .quiz {
                state = .score
                updateUI()
                return
            }
            
        }
        
        state = .question
        updateUI()
        if currentElementIndex >= 4{
            let alert = UIAlertController(title:"Try again", message: "This element seems to be giving you trouble, why don't you try it again", preferredStyle: .alert)
            
            let dismissAction =
            UIAlertAction(title: "OK",
                          style: .default)
            alert.addAction(dismissAction)
            
            present(alert, animated: true,
                    completion: nil)
        }
    }
    @IBAction func switchModes(_ sender: Any) {
        
        if modeSelector.selectedSegmentIndex == 0 {
            mode = .flashCard
        } else {
            mode = .quiz
        }
    }
    
    @IBOutlet weak var modeSelector: UISegmentedControl!
    func updateFlashCardUI(elementName : String) {
        //print("Update FC UI")
        modeSelector.selectedSegmentIndex = 0
        showAnswer.isHidden = false
        nextButton.isEnabled = true
        nextButton.setTitle("Next Element", for: .normal)
        if state == .question {
            answerLabel.text = "?"
        } else {
            answerLabel.text = elementName
        }
        textField.isHidden = true
        textField.resignFirstResponder()
    }
    
    @IBOutlet weak var nextButton: UIButton!
    func updateQuizUI(elementName : String) {
        showAnswer.isHidden = true
        modeSelector.selectedSegmentIndex = 1
        textField.isHidden = false
        if currentElementIndex == elementList.count - 1 {
            nextButton.setTitle("Show Score",
               for: .normal)
        } else {
            nextButton.setTitle("Next Question",
               for: .normal)
        }
        switch state{
        case .question:
            textField.isEnabled = true
            textField.text = ""
            textField.becomeFirstResponder()
            nextButton.isEnabled = false
        case .answer:
            textField.isEnabled = false
            textField.resignFirstResponder()
            nextButton.isEnabled = true
        case .score:
            textField.isHidden = false
            textField.resignFirstResponder()
            
        }
        
        switch state{
            
        case .question:
            answerLabel.text = ""
            
        case .answer:
            if answerIsCorrect {
                answerLabel.text = "Correct"
            } else {
                answerLabel.text = "❌\nCorrect answer: \(elementName)"
                wrongAnswer[elementName] = wrongAnswer[elementName]! + 1
            }
        case .score:
            displayScoreAlert()
            
        }
        
        
    }
    
    
    @IBOutlet weak var textField: UITextField!
    func textFieldShouldReturn(_ textField : UITextField) -> Bool {
        print("test")
        let textFieldContents = textField.text!
        
        if textFieldContents.lowercased() == elementList[currentElementIndex].lowercased() {
            
            correctAnswerCount += 1
            answerIsCorrect = true
            
        } else {
            
            answerIsCorrect = false
            
        }
        
        state = .answer
        updateUI()
        
        return true
        
    }
    
    
    func updateUI(){
        let elementName = elementList[currentElementIndex]
        let image = UIImage(named: elementName)
        imageView.image = image
        
        switch mode {
            
        case .flashCard:
            updateFlashCardUI(elementName: elementName)
            
        case .quiz:
            updateQuizUI(elementName: elementName)
        }
        
    }
    func displayScoreAlert() {
        let alert = UIAlertController(title:"Quiz Score", message: "Your score is \(correctAnswerCount) out of \(elementList.count).", preferredStyle: .alert)
        
        let dismissAction =
        UIAlertAction(title: "OK",
                      style: .default, handler:
                        scoreAlertDismissed(_:))
        alert.addAction(dismissAction)
        
        present(alert, animated: true,
                completion: nil)
    }
    
    @IBOutlet weak var showAnswer: UIButton!
    func scoreAlertDismissed(_ action: UIAlertAction) {
        mode = .flashCard
    }
    func setupFlashcards() {
        var mostCommonWrongAnswer : [String] = []
        var numberWrong : Int = 0
        for (element, number) in wrongAnswer {
            if (wrongAnswer["Chlorine"] == wrongAnswer["Sodium"] && wrongAnswer["Chlorine"] == wrongAnswer["Carbon"] && wrongAnswer["Chlorine"] == wrongAnswer["Gold"]) {
                mostCommonWrongAnswer = []
            }
             else if number > numberWrong {
                mostCommonWrongAnswer = []
                mostCommonWrongAnswer.append(element)
                numberWrong = number
            }
            else if number == numberWrong {
                mostCommonWrongAnswer.append(element)
            }
        }
//print(mostCommonWrongAnswer)
        //print(wrongAnswer["Chlorine"] == wrongAnswer["Sodium"] && wrongAnswer["Chlorine"] == wrongAnswer["Carbon"] && wrongAnswer["Chlorine"] == wrongAnswer["Gold"])
        elementList = fixedElementList
        if mostCommonWrongAnswer.count > 0 {
            for answer in mostCommonWrongAnswer {
                elementList.append(answer)
            }
            
        }
        state = .question
        currentElementIndex = 0
        
    }
    func setupQuizzes() {
        elementList = fixedElementList.shuffled()
        state = .question
        currentElementIndex = 0
        answerIsCorrect = false
        correctAnswerCount = 0
        
    }
}
