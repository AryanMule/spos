import tkinter as tk
from tkinter import messagebox

# Function to submit feedback
def submit_feedback():
    name = entry_name.get()
    feedback = text_feedback.get("1.0", tk.END)
    food_quality = food_quality_var.get()
    
    if name and feedback.strip():
        messagebox.showinfo("Feedback Submitted", "Thank you for your feedback!")
    else:
        messagebox.showwarning("Input Error", "Name and feedback are required.")

# Creating the main window
root = tk.Tk()
root.title("Customer Feedback Form")

# Name Label and Entry
label_name = tk.Label(root, text="Your Name:")
label_name.grid(row=0, column=0, padx=10, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=1, padx=10, pady=5)

# Food Quality Label
label_food_quality = tk.Label(root, text="Food Quality:")
label_food_quality.grid(row=1, column=0, padx=10, pady=5)

# Checkbuttons for food quality
food_quality_var = tk.StringVar(value="Good")
check_good = tk.Checkbutton(root, text="Good", variable=food_quality_var, onvalue="Good")
check_good.grid(row=1, column=1, sticky='w')

check_average = tk.Checkbutton(root, text="Average", variable=food_quality_var, onvalue="Average")
check_average.grid(row=1, column=1, sticky='e')

check_poor = tk.Checkbutton(root, text="Poor", variable=food_quality_var, onvalue="Poor")
check_poor.grid(row=1, column=1, sticky='se')

# Feedback Label and Text Widget
label_feedback = tk.Label(root, text="Feedback:")
label_feedback.grid(row=2, column=0, padx=10, pady=5)
text_feedback = tk.Text(root, height=5, width=30)
text_feedback.grid(row=2, column=1, padx=10, pady=5)

# Submit Button
submit_button = tk.Button(root, text="Submit", command=submit_feedback)
submit_button.grid(row=3, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
