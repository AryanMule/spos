import tkinter as tk
from tkinter import messagebox

# Function to submit the form
def submit_form():
    name = entry_name.get()
    age = entry_age.get()
    gender = gender_var.get()
    courses = [listbox_courses.get(i) for i in listbox_courses.curselection()]
    agree = agree_var.get()

    if not name or not age:
        messagebox.showwarning("Input Error", "Name and Age fields cannot be empty.")
        return

    if not courses:
        messagebox.showwarning("Input Error", "Please select at least one course.")
        return

    if agree == 0:
        messagebox.showwarning("Agreement", "You must agree to the terms and conditions.")
        return

    # Displaying the submitted data
    messagebox.showinfo("Registration Success", f"Name: {name}\nAge: {age}\nGender: {gender}\nCourses: {', '.join(courses)}\nRegistration Successful!")

# Creating the main window
root = tk.Tk()
root.title("Student Registration Form")

# Label and Entry for Name
label_name = tk.Label(root, text="Name:")
label_name.grid(row=0, column=0, padx=10, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=1, padx=10, pady=5)

# Label and Entry for Age
label_age = tk.Label(root, text="Age:")
label_age.grid(row=1, column=0, padx=10, pady=5)
entry_age = tk.Entry(root)
entry_age.grid(row=1, column=1, padx=10, pady=5)

# Label and Checkbuttons for Gender
label_gender = tk.Label(root, text="Gender:")
label_gender.grid(row=2, column=0, padx=10, pady=5)
gender_var = tk.StringVar(value="None")
check_male = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male")
check_female = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female")
check_male.grid(row=2, column=1, sticky='w')
check_female.grid(row=2, column=1, sticky='e')

# Label and Listbox for Course Selection
label_courses = tk.Label(root, text="Courses:")
label_courses.grid(row=3, column=0, padx=10, pady=5)
listbox_courses = tk.Listbox(root, selectmode="multiple", height=5)
courses = ["Math", "Science", "History", "Art", "Computer Science"]
for course in courses:
    listbox_courses.insert(tk.END, course)
listbox_courses.grid(row=3, column=1, padx=10, pady=5)

# Checkbutton for agreement
agree_var = tk.IntVar()
check_agree = tk.Checkbutton(root, text="I agree to the terms and conditions", variable=agree_var)
check_agree.grid(row=4, columnspan=2, pady=5)

# Submit button
submit_button = tk.Button(root, text="Submit", command=submit_form)
submit_button.grid(row=5, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
