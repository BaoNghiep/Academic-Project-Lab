using System;
using System.Collections.Generic;

namespace DAL.Entities;

public partial class Event
{
    public int EventId { get; set; }

    public string? EventTitle { get; set; }

    public string? Description { get; set; }

    public DateOnly? EventDate { get; set; }

    public int? VenueId { get; set; }

    public string? Status { get; set; }

    public string? CreatedBy { get; set; }

    public decimal? TicketPrice { get; set; }

    public virtual Venue? Venue { get; set; }
}
