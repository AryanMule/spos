import tkinter as tk
from tkinter import messagebox

# Function to handle booking
def book_cab():
    name = entry_name.get()
    pickup_location = entry_pickup.get()
    destination = entry_destination.get()
    vehicle_type = vehicle_var.get()

    if name and pickup_location and destination and vehicle_type:
        messagebox.showinfo("Booking Successful", f"Booking Confirmed!\nName: {name}\nPickup: {pickup_location}\nDestination: {destination}\nVehicle: {vehicle_type}")
    else:
        messagebox.showwarning("Input Error", "Please fill all fields.")

# Creating the main booking window
root = tk.Tk()
root.title("Cab/Auto Booking App")

# Name Label and Entry
label_name = tk.Label(root, text="Name:")
label_name.grid(row=0, column=0, padx=10, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=1, padx=10, pady=5)

# Pickup Location Label and Entry
label_pickup = tk.Label(root, text="Pickup Location:")
label_pickup.grid(row=1, column=0, padx=10, pady=5)
entry_pickup = tk.Entry(root)
entry_pickup.grid(row=1, column=1, padx=10, pady=5)

# Destination Label and Entry
label_destination = tk.Label(root, text="Destination:")
label_destination.grid(row=2, column=0, padx=10, pady=5)
entry_destination = tk.Entry(root)
entry_destination.grid(row=2, column=1, padx=10, pady=5)

# Vehicle Type Label and Radio Buttons
label_vehicle = tk.Label(root, text="Vehicle Type:")
label_vehicle.grid(row=3, column=0, padx=10, pady=5)
vehicle_var = tk.StringVar(value="Cab")
radio_cab = tk.Radiobutton(root, text="Cab", variable=vehicle_var, value="Cab")
radio_auto = tk.Radiobutton(root, text="Auto", variable=vehicle_var, value="Auto")
radio_cab.grid(row=3, column=1, sticky='w')
radio_auto.grid(row=3, column=1, sticky='e')

# Book Button
book_button = tk.Button(root, text="Book Now", command=book_cab)
book_button.grid(row=4, columnspan=2, pady=20)

# Run the main loop
root.mainloop()
