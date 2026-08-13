using System;
using System.Collections.Generic;

namespace DAL.Entities;

public partial class Venue
{
    public int VenueId { get; set; }

    public string? VenueName { get; set; }

    public string? Location { get; set; }

    public int? Capacity { get; set; }

    public string? CreatedBy { get; set; }

    public virtual ICollection<Event> Events { get; set; } = new List<Event>();
}
