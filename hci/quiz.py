import tkinter as tk
from tkinter import messagebox

# Function to check the answer
def check_answer():
    selected = answer_var.get()
    if selected == correct_answer:
        messagebox.showinfo("Result", "Correct!")
    else:
        messagebox.showerror("Result", "Incorrect. Try again!")

# Creating the main window
root = tk.Tk()
root.title("Online Quiz")

# Question
label_question = tk.Label(root, text="What is the capital of France?")
label_question.pack(pady=10)

# Correct answer variable
correct_answer = "Paris"

# Answer options using Radiobuttons
answer_var = tk.StringVar(value="None")
options = ["London", "Paris", "Berlin", "Madrid"]

for option in options:
    tk.Radiobutton(root, text=option, variable=answer_var, value=option).pack(anchor='w')

# Submit button
submit_button = tk.Button(root, text="Submit", command=check_answer)
submit_button.pack(pady=10)

# Run the main loop
root.mainloop()
